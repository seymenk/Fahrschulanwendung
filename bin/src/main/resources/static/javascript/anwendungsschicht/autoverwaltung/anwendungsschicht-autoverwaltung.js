document.addEventListener('DOMContentLoaded', function() {
	const optionButtons = document.querySelectorAll('.optionen button');

	optionButtons[0].addEventListener('click', function() {
		window.location.href = '/autoverwaltung-uebersicht';
	});

	optionButtons[1].addEventListener('click', function() {
		window.location.href = '/autoverwaltung-hinzufuegen';
	});

	optionButtons[2].addEventListener('click', function() {
		window.location.href = '/autoverwaltung-aendern';
	});

	optionButtons[3].addEventListener('click', function() {
		window.location.href = '/autoverwaltung-loeschen';
	});
});