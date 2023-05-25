async function changePassword(username, oldPassword, newPassword) {
    const response = await fetch('/change-password', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: username,
            oldPassword: oldPassword,
            newPassword: newPassword
        })
    });

    if (response.ok) {
        return await response.json();
    } else {
        return false;
    }
}