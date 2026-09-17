package com.openlattice.chronicle.users

/**
 * How a user signs in. Derived from the identity provider backing an Auth0 identity, so that a study admin granting
 * access can tell a password account apart from a federated one before handing out permissions.
 *
 * @author Matthew Tamayo-Rios &lt;matthew@getmethodic.com&gt;
 */
enum class LoginType {
    /** An Auth0 database connection, i.e. an email + password account. */
    USERNAME_PASSWORD,

    /** A social identity provider federated over OAuth2 -- Google, Apple, Facebook, GitHub, ... */
    OAUTH,

    /** An enterprise connection -- SAML, ADFS, Azure AD, Okta, OIDC, ... */
    ENTERPRISE,

    /** A passwordless connection, i.e. a one time code delivered over email or sms. */
    PASSWORDLESS,

    /** The user has no identities, or none backed by a provider we recognize. */
    UNKNOWN;

    companion object {
        private val ENTERPRISE_PROVIDERS = setOf(
            "ad",
            "adfs",
            "custom",
            "google-apps",
            "oidc",
            "okta",
            "pingfederate",
            "samlp",
            "waad",
        )

        /**
         * Classifies a single Auth0 identity by its provider, falling back to the connection name when the provider is
         * missing. Auth0 reports the database connection as the `auth0` provider and every social connection as the
         * provider's own name, so anything that isn't a database, passwordless, or enterprise provider is OAuth.
         */
        @JvmStatic
        fun fromProvider(provider: String?, connection: String? = null): LoginType {
            val id = (provider ?: connection)?.lowercase() ?: return UNKNOWN
            return when {
                id == "auth0" -> USERNAME_PASSWORD
                id == "email" || id == "sms" -> PASSWORDLESS
                ENTERPRISE_PROVIDERS.contains(id) -> ENTERPRISE
                else -> OAUTH
            }
        }

        /**
         * Collapses the login types of all of a user's identities into one. A user with a password account linked to a
         * social account is reported as [USERNAME_PASSWORD], since that is the credential they can sign in with
         * directly.
         */
        @JvmStatic
        fun resolve(loginTypes: Collection<LoginType>): LoginType = loginTypes
            .filter { it != UNKNOWN }
            .minByOrNull { it.ordinal } ?: UNKNOWN
    }
}
