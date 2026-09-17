package com.openlattice.chronicle.users

import org.junit.Assert
import org.junit.Test

class LoginTypeTest {

    @Test
    fun testDatabaseConnectionIsUsernamePassword() {
        Assert.assertEquals(
            LoginType.USERNAME_PASSWORD,
            LoginType.fromProvider("auth0", "Username-Password-Authentication")
        )
    }

    @Test
    fun testSocialProvidersAreOauth() {
        listOf("google-oauth2", "apple", "facebook", "github", "windowslive", "linkedin").forEach { provider ->
            Assert.assertEquals("$provider should be OAuth", LoginType.OAUTH, LoginType.fromProvider(provider))
        }
    }

    @Test
    fun testEnterpriseProviders() {
        listOf("samlp", "waad", "adfs", "google-apps", "okta", "oidc", "ad", "pingfederate").forEach { provider ->
            Assert.assertEquals(
                "$provider should be enterprise",
                LoginType.ENTERPRISE,
                LoginType.fromProvider(provider)
            )
        }
    }

    @Test
    fun testPasswordlessProviders() {
        Assert.assertEquals(LoginType.PASSWORDLESS, LoginType.fromProvider("email"))
        Assert.assertEquals(LoginType.PASSWORDLESS, LoginType.fromProvider("sms"))
    }

    @Test
    fun testProviderMatchingIsCaseInsensitive() {
        Assert.assertEquals(LoginType.USERNAME_PASSWORD, LoginType.fromProvider("Auth0"))
        Assert.assertEquals(LoginType.ENTERPRISE, LoginType.fromProvider("SAMLP"))
    }

    @Test
    fun testConnectionIsUsedWhenProviderIsMissing() {
        Assert.assertEquals(LoginType.USERNAME_PASSWORD, LoginType.fromProvider(null, "auth0"))
        Assert.assertEquals(LoginType.UNKNOWN, LoginType.fromProvider(null, null))
    }

    @Test
    fun testResolvePrefersTheCredentialAUserCanSignInWithDirectly() {
        Assert.assertEquals(
            LoginType.USERNAME_PASSWORD,
            LoginType.resolve(listOf(LoginType.OAUTH, LoginType.USERNAME_PASSWORD))
        )
        Assert.assertEquals(
            LoginType.OAUTH,
            LoginType.resolve(listOf(LoginType.ENTERPRISE, LoginType.OAUTH))
        )
    }

    @Test
    fun testResolveIgnoresUnknownUnlessItIsAllThereIs() {
        Assert.assertEquals(LoginType.OAUTH, LoginType.resolve(listOf(LoginType.UNKNOWN, LoginType.OAUTH)))
        Assert.assertEquals(LoginType.UNKNOWN, LoginType.resolve(listOf(LoginType.UNKNOWN)))
        Assert.assertEquals(LoginType.UNKNOWN, LoginType.resolve(listOf()))
    }
}
