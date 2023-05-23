// Load Fahrlehrer
document.addEventListener("DOMContentLoaded", () => {
	getAllFahrlehrer().then((fahrlehrerList) => {
		const fahrlehrerDropdown = document.getElementById("fahrlehrer-dropdown");
		fahrlehrerList.forEach((fahrlehrer) => {
			const option = document.createElement("option");
			option.value = fahrlehrer.id;
			option.textContent = fahrlehrer.vorname + " " + fahrlehrer.nachname;
			fahrlehrerDropdown.appendChild(option);
		});
		fahrlehrerDropdown.addEventListener("change", enableCalendar);
		enableCalendar();
	});
});

// Enable Calendar
function enableCalendar() {
	const fahrlehrerDropdown = document.getElementById("fahrlehrer-dropdown");
	const dateInput = document.getElementById("date-input");
	if (fahrlehrerDropdown.value !== "") {
		dateInput.disabled = false;
	} else {
		dateInput.disabled = true;
	}
	dateInput.addEventListener("input", () => {
		checkWeekday();
		fetchAvailableTimeSlots();
	});
	fetchAvailableTimeSlots();
}

// Check Weekday
function checkWeekday() {
	const dateInput = document.getElementById("date-input");
	const errorMessage = document.getElementById("error-message");

	if (isWeekend(dateInput.value)) {
		errorMessage.style.display = "block";
		dateInput.value = "";
		disableTimeSlot();
	} else {
		errorMessage.style.display = "none";
		enableTimeSlot();
	}
}

// Disable Time Slot
function disableTimeSlot() {
	const timeSlotDropdown = document.getElementById("time-slot-dropdown");
	timeSlotDropdown.disabled = true;
	timeSlotDropdown.value = "";
}

// Enable Time Slot
function enableTimeSlot() {
	const timeSlotDropdown = document.getElementById("time-slot-dropdown");
	timeSlotDropdown.disabled = false;
	timeSlotDropdown.value = "";
	fetchAvailableTimeSlots();
}

// Fetch Available Time Slots
function fetchAvailableTimeSlots() {
	const fahrlehrerSelect = document.getElementById("fahrlehrer-dropdown");
	const fahrlehrerID = fahrlehrerSelect.value;

	const dateInput = document.getElementById("date-input");
	const selectedDate = dateInput.value;

	if (!selectedDate) {
		return;
	}

	const data = {
		fahrlehrerID: fahrlehrerID,
		date: selectedDate,
	};

	getIDandDate(data).then((termine) => updateTimeslotsDropdown(termine));
}

// Update Timeslots Dropdown
function updateTimeslotsDropdown(termine) {
	const timeSlotDropdown = document.getElementById("time-slot-dropdown");

	timeSlotDropdown.innerHTML =
		'<option value="" selected>Wählen Sie einen Terminslot</option>';

	termine.forEach((termin) => {
		const option = document.createElement("option");
		option.value = termin.id;

		const startTimeString = termin.startUhrzeit;
		const endTimeString = termin.endUhrzeit;

		option.textContent = startTimeString + " - " + endTimeString;
		timeSlotDropdown.appendChild(option);
	});
}

// Book Termin
function bookTermin() {
	const fahrlehrerDropdown = document.getElementById("fahrlehrer-dropdown");
	const dateInput = document.getElementById("date-input");
	const timeSlotDropdown = document.getElementById("time-slot-dropdown");

	if (
		fahrlehrerDropdown.value === "" ||
		dateInput.value === "" ||
		timeSlotDropdown.value === ""
	) {
		alert("Bitte füllen Sie alle Felder aus, bevor Sie fortfahren.");
		return;
	}

	const data = {
		id: timeSlotDropdown.value,
	};

	bookTerminById(data)
		.then((result) => {
			if (result.success) {
				alert("Termin erfolgreich gebucht!");
			} else {
				alert(
					"Es gab ein Problem bei der Buchung des Termins. Bitte versuchen Sie es später erneut."
				);
			}
		})
		.finally(() => {
			location.reload();
		});
}

document.getElementById("book-termin-button").addEventListener("click", bookTermin);
