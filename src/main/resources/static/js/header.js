document.addEventListener('DOMContentLoaded', () => {
    const searchInput = document.getElementById("character-search");
    searchInput.addEventListener('keydown', (e) => {
        if(e.key === "Enter") {
            this.location.href = "/character/" + searchInput.value;
        }
    })
});