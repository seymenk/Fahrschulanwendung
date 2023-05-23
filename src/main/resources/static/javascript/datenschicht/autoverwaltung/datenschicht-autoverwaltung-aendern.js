async function getCarData(id) {
	const carResponse = await fetch(`/get-car?id=${id}`);
	return await carResponse.json();
}

async function changeCarData(auto) {
	return await fetch("/change-auto", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(auto),
	});
}
