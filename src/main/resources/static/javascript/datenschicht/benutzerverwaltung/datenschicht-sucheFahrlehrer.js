const api = {
	async searchFahrlehrer(vorname, nachname) {
		const xhttp = new XMLHttpRequest();
		return new Promise((resolve, reject) => {
			xhttp.onreadystatechange = function () {
				if (this.readyState === 4 && this.status === 200) {
					try {
						const data = JSON.parse(this.responseText);
						resolve(data);
					} catch (error) {
						reject(new Error("Fehler bei der Verarbeitung der API-Antwort"));
					}
				} else if (this.readyState === 4) {
					reject(new Error("Fehler bei der Suche nach Fahrlehrern"));
				}
			};
			xhttp.open("GET", "/suche-api-fahrlehrer?vorname=" + encodeURIComponent(vorname) + "&nachname=" + encodeURIComponent(nachname), true);
			xhttp.send();
		});
	},
};