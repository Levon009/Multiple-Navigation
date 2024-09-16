package com.example.jetpack_multiplenavigation.cipherManager

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.io.InputStream
import java.io.OutputStream
import java.security.KeyStore
import java.security.KeyStore.SecretKeyEntry
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class CipherManager {
    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    private val encryptCipher = Cipher.getInstance(TRANSFORMATION).apply {
        init(Cipher.ENCRYPT_MODE, getSecretKey())
    }

    private fun getDecryptCipherForIv(iv: ByteArray) : Cipher {
        return Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, getSecretKey(), IvParameterSpec(iv))
        }
    }

    private fun createSecretKey() : SecretKey {
        return KeyGenerator.getInstance(ALGORITHM).apply {
            init(
                KeyGenParameterSpec.Builder(
                    "LionSecret",
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(BLOCK_MODE)
                    .setEncryptionPaddings(PADDING)
                    .setUserAuthenticationRequired(false)
                    .setRandomizedEncryptionRequired(true)
                    .build()
            )
        }.generateKey()
    }

    private fun getSecretKey() : SecretKey {
        val existingKey = keyStore.getEntry("LionSecret", null) as? SecretKeyEntry
        return existingKey?.secretKey ?: createSecretKey()
    }

    fun encrypt(bytes: ByteArray, outputStream: OutputStream) : ByteArray {
        val encryptedBytes = encryptCipher.doFinal(bytes)
        outputStream.use { output ->
            output.write(encryptCipher.iv.size)
            output.write(encryptCipher.iv)
            output.write(encryptedBytes.size)
            output.write(encryptedBytes)
        }

        return encryptedBytes
    }

    fun decrypt(inputStream: InputStream) : ByteArray {
        return inputStream.use { input ->
            val ivSize = input.read()
            val iv = ByteArray(ivSize)
            input.read(iv)

            val encryptedBytesSize = input.read()
            val encryptedBytes = ByteArray(encryptedBytesSize)
            input.read(encryptedBytes)

            getDecryptCipherForIv(iv).doFinal(encryptedBytes)
        }
    }

    companion object {
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
    }
}