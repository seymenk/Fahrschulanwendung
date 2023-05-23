document
	.getElementById("submit-timeslots")
	.addEventListener("click", function() {
		const cards = document.querySelectorAll(".timeslot-card");
		const terminObjekte = [];

		cards.forEach((card) => {
			const terminListe = createTerminFromCard(card);
			if (terminListe.length > 0) {
				terminObjekte.push(...terminListe);
			}
		});

		fetch("/terminslots", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(terminObjekte),
		})
			.then((response) => {
				if (response.ok) {
					console.log("Terminobjekte erfolgreich gesendet.");
					alert("Timeslots hinzugefügt.");
				} else {
					throw new Error(
						"Ein Fehler ist aufgetreten: " + response.status
					);
				}
			})
			.catch((error) => {
				console.error("Fehler beim Senden der Terminobjekte:", error);
				alert("Fehler beim Senden der Terminobjekte.");
			});
	});