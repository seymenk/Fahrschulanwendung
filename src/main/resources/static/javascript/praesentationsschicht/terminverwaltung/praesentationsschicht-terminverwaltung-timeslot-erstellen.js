document.addEventListener("DOMContentLoaded", function () {
function createTimeslotCard() {
	const card = document.createElement("div");
	card.className = "timeslot-card";
	card.innerHTML = `<div class="calendar-container"> <label for="date-input">Datum:</label> <input type="date" class="date-input" /> <div class="error-message" style="display: none; color: red"> Bitte wählen Sie einen Wochentag (Montag bis Freitag) aus. </div> </div> <label for="">Uhrzeit Vorauswahl:</label> <select disabled> <option value="" selected>Wählen Sie einen Terminslot</option> <option value="slot1">08:00 - 09:00</option> <option value="slot2">09:00 - 10:00</option> <option value="slot3">10:30 - 11:30</option> <option value="slot4">11:30 - 12:30</option> <option value="slot5">13:00 - 14:00</option> <option value="slot6">14:00 - 15:00</option> <option value="slot7">15:00 - 16:00</option> <option value="slot8">16:00 - 17:00</:00</option> </select> <br /> <label for="">Startuhrzeit (manuelle Eingabe):</label> <input type="text" disabled/> <div class="time-error-message" style="display: none; color: red"> Bitte geben Sie eine gültige Uhrzeit im Format HH:mm ein. </div> <br /> <label for="auto-timeslots">Automatisch Timeslots für das Datum erstellen:</label> <input type="checkbox" disabled/>`;
	const dateInput = card.querySelector(".date-input");
	const select = card.querySelector("select");
	const manualTimeInput = card.querySelector("input[type='text']");
	const autoTimeslotsCheckbox = card.querySelector(
		"input[type='checkbox']"
	);

	dateInput.addEventListener("change", function() {
		const inputDate = new Date(dateInput.value);
		const day = inputDate.getDay();
		const errorMessage =
			dateInput.parentElement.querySelector(".error-message");

		if (day === 0 || day === 6 || isNaN(day)) {
			errorMessage.style.display =
				day === 0 || day === 6 ? "block" : "none";
			dateInput.value = day === 0 || day === 6 ? "" : dateInput.value;
			toggleInputs(card, false);
		} else {
			errorMessage.style.display = "none";
			toggleInputs(card, true);
		}
	});

	select.addEventListener("change", function() {
		handleInputChanges(card);
	});

	manualTimeInput.addEventListener("input", function() {
		const regex = /^([0-1][0-9]|2[0-3]):[0-5][0-9]$/;
		const errorMessage = manualTimeInput.parentElement.querySelector(
			".time-error-message"
		);

		if (
			regex.test(manualTimeInput.value) ||
			manualTimeInput.value === ""
		) {
			errorMessage.style.display = "none";
		} else {
			errorMessage.style.display = "block";
		}
		handleInputChanges(card);
	});

	autoTimeslotsCheckbox.addEventListener("change", function() {
		handleInputChanges(card);
	});

	return card;
}

document.getElementById("add-card-btn").addEventListener("click", function() {
	const newCard = createTimeslotCard();
	document.getElementById("cards-container").appendChild(newCard);
});


function toggleInputs(card, enabled) {
	const elements = card.querySelectorAll(
		"select, input[type='text'], input[type='checkbox']"
	);
	elements.forEach((element) => {
		element.disabled = !enabled;
	});
}

function handleInputChanges(card) {
	const select = card.querySelector("select");
	const manualTimeInput = card.querySelector("input[type='text']");
	const autoTimeslotsCheckbox = card.querySelector(
		"input[type='checkbox']"
	);

	if (select.value !== "") {
		manualTimeInput.disabled = true;
		autoTimeslotsCheckbox.disabled = true;
	} else if (manualTimeInput.value !== "") {
		select.disabled = true;
		autoTimeslotsCheckbox.disabled = true;
	} else if (autoTimeslotsCheckbox.checked) {
		select.disabled = true;
		manualTimeInput.disabled = true;
	} else {
		select.disabled = false;
		manualTimeInput.disabled = false;
		autoTimeslotsCheckbox.disabled = false;
	}
}});