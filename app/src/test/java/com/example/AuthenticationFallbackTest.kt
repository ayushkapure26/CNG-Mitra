package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.util.AuthResult
import com.example.util.FirebaseAuthManager
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35])
class AuthenticationFallbackTest {
    @Test fun missingFirebaseCannotAuthenticateOrResetPasswords() = runBlocking {
        val manager = FirebaseAuthManager()
        assertTrue(manager.signInWithEmailAndPassword("driver@example.com", "password123") is AuthResult.Error)
        assertTrue(manager.createUserWithEmailAndPassword("driver@example.com", "password123", "Driver") is AuthResult.Error)
        assertTrue(manager.sendPasswordResetEmail("driver@example.com").isFailure)
        val context = ApplicationProvider.getApplicationContext<Context>()
        assertTrue(manager.signInWithGoogle(context) is AuthResult.Error)
    }
}
