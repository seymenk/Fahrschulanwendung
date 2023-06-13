document.addEventListener('DOMContentLoaded', function() {
    fetch('/messages')
        .then(response => response.json())
        .then(messages => {
            for (let key in messages) {
                let element = document.getElementById(key);
                if (element) {
                    element.innerHTML = messages[key];
                }
            }
        });
});
