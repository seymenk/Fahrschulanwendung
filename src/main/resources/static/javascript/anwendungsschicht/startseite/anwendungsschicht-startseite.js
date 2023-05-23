document.addEventListener("DOMContentLoaded", function() {
	const slides = document.querySelectorAll(".slide");
	const dots = document.querySelectorAll(".dot");
	let currentIndex = 0;
	function changeSlide(direction) {
		slides[currentIndex].classList.remove("active");
		dots[currentIndex].classList.remove("active");
		document
			.querySelectorAll(".slide-info")
		[currentIndex].classList.remove("active");

		currentIndex += direction;

		if (currentIndex < 0) {
			currentIndex = slides.length - 1;
		} else if (currentIndex >= slides.length) {
			currentIndex = 0;
		}

		slides[currentIndex].classList.add("active");
		dots[currentIndex].classList.add("active");
		document
			.querySelectorAll(".slide-info")
		[currentIndex].classList.add("active");
		resetInterval();
	}

	function jumpToSlide(index) {
		if (index === currentIndex) {
			return;
		}

		slides[currentIndex].classList.remove("active");
		dots[currentIndex].classList.remove("active");
		document
			.querySelectorAll(".slide-info")
		[currentIndex].classList.remove("active");

		currentIndex = index;

		slides[currentIndex].classList.add("active");
		dots[currentIndex].classList.add("active");
		document
			.querySelectorAll(".slide-info")
		[currentIndex].classList.add("active");
		resetInterval();
	}

	document
		.querySelector(".arrow-left")
		.addEventListener("click", () => changeSlide(-1));
	document
		.querySelector(".arrow-right")
		.addEventListener("click", () => changeSlide(1));
	dots.forEach((dot, index) =>
		dot.addEventListener("click", () => jumpToSlide(index))
	);

	let autoSlideInterval;

	function startInterval() {
		autoSlideInterval = setInterval(() => changeSlide(1), 3000);
	}

	function resetInterval() {
		clearInterval(autoSlideInterval);
		startInterval();
	}

	startInterval();
});
