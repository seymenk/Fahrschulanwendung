document.addEventListener('DOMContentLoaded', function() {
	const optionButtons = document.querySelectorAll('.optionen button');

	optionButtons[0].addEventListener('click', function() {
		window.location.href = '/suche-fahrschueler';
	});

	optionButtons[1].addEventListener('click', function() {
		window.location.href = '/suche-fahrlehrer';
	});

	optionButtons[2].addEventListener('click', function() {
		window.location.href = '/benutzerverwaltung-anlegen';
	});

	optionButtons[3].addEventListener('click', function() {
		window.location.href = '/benutzerverwaltung-aendern';
	});
	
	optionButtons[4].addEventListener('click', function() {
		window.location.href = '/benutzerverwaltung-loeschen';
	});
});