async function deleteCarById(id) {
	return await fetch(`/cars/${id}`, {
		method: "DELETE",
	});
}
