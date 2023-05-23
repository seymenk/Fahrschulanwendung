/**
 * Das Passwortpaket.
 */
package softwaretechnik2.fahrschulanwendung.passwort.anwendungsschicht;

/**
 * Definiert die PasswortChangeRequest-Klasse, die für das Speichern von
 * Informationen zum Ändern von Passwörtern verwendet wird.
 */
public class PasswortChangeRequest {
    // Benutzername
    private String username;
    // Altes Passwort
    private String oldPassword;
    // Neues Passwort
    private String newPassword;

    /**
     * Getter für den Benutzernamen.
     *
     * @return den Benutzernamen als String.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter für den Benutzernamen.
     *
     * @param username der neue Benutzername als String.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter für das alte Passwort.
     *
     * @return das alte Passwort als String.
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * Setter für das alte Passwort.
     *
     * @param oldPassword das alte Passwort als String.
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * Getter für das neue Passwort.
     *
     * @return das neue Passwort als String.
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Setter für das neue Passwort.
     *
     * @param newPassword das neue Passwort als String.
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}