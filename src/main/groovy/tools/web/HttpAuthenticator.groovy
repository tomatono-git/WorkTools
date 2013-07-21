/**
 *
 */
package tools.web

/** Basic”FØ‚Ì‚½‚ß‚ÌƒNƒ‰ƒX */
class HttpAuthenticator extends Authenticator {

	def username
	def password

	/* (”ñ Javadoc)
	 * @see java.net.Authenticator#getPasswordAuthentication()
	 */
	def PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.username, this.password as char[])
	}

	def String getPrompt() {
		return super.getRequestingPrompt()
	}
}

