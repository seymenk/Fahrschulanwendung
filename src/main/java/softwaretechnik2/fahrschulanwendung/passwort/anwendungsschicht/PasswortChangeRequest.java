package softwaretechnik2.fahrschulanwendung.passwort.anwendungsschicht;

/**
 * Definiert die PasswortChangeRequest-Klasse, die für das Speichern von
 * Informationen zum Ändern von Passwörtern verwendet wird.
 * @author seymen
 */
public class PasswortChangeRequest {
    private String username;
    private String oldPassword;
    private String newPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}