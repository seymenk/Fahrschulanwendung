const createTerminObjekt = (startUhrzeit, datum) => ({
	startUhrzeit,
	datum,
});

function createTerminFromCard(card) {
	const dateInput = card.querySelector(".date-input");
	const select = card.querySelector("select");
	const manualTimeInput = card.querySelector("input[type='text']");
	const autoTimeslotsCheckbox = card.querySelector(
		"input[type='checkbox']"
	);

	const datum = dateInput.value;
	const terminObjekte = [];

	if (select.value !== "") {
		const selectedOption = select.options[select.selectedIndex];
		const timeRange = selectedOption.text.split(" - ");
		const startuhrzeit = timeRange[0];
		terminObjekte.push(createTerminObjekt(startuhrzeit, datum));
	} else if (manualTimeInput.value !== "") {
		const startuhrzeit = manualTimeInput.value;
		terminObjekte.push(createTerminObjekt(startuhrzeit, datum));
	} else if (autoTimeslotsCheckbox.checked) {
		const timeSlots = [
			"08:00",
			"09:00",
			"10:30",
			"11:30",
			"13:00",
			"14:00",
			"15:00",
			"16:00",
		];

		timeSlots.forEach((timeSlot) => {
			terminObjekte.push(createTerminObjekt(timeSlot, datum));
		});
	}
	return terminObjekte;
}