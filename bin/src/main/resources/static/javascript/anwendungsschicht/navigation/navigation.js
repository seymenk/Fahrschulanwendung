document.addEventListener('DOMContentLoaded', function() {
	const middleNavButtons = document.querySelectorAll('.middle .nav-button');
	const rightNavButtons = document.querySelectorAll('.right .nav-button');

	middleNavButtons[0].addEventListener('click', function() {
		window.location.href = '/startseite';
	});

	middleNavButtons[1].addEventListener('click', function() {
		window.location.href = '/benutzerverwaltung';
	});

	middleNavButtons[2].addEventListener('click', function() {
		window.location.href = '/autoverwaltung';
	});

	middleNavButtons[3].addEventListener('click', function() {
		window.location.href = '/terminverwaltung';
	});

	rightNavButtons[0].addEventListener('click', function() {
		window.location.href = '/konto';
	});

	rightNavButtons[1].addEventListener('click', function() {
		window.location.href = '/agb';
	});

	rightNavButtons[2].addEventListener('click', function() {
		window.location.href = '/impressum';
	});
});
