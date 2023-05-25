document.addEventListener('DOMContentLoaded', function() {
	const optionButtons = document.querySelectorAll('.optionen button');

	optionButtons[0].addEventListener('click', function() {
		window.location.href = '/terminverwaltung-timeslot-erstellen';
	});

	optionButtons[1].addEventListener('click', function() {
		window.location.href = '/terminverwaltung-meine-termine';
	});

	optionButtons[2].addEventListener('click', function() {
		window.location.href = '/terminverwaltung-alle-termine';
	});

	optionButtons[3].addEventListener('click', function() {
		window.location.href = '/terminverwaltung-loeschen';
	});
});