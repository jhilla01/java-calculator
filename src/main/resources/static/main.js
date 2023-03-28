document.querySelectorAll(".number").forEach(button => {
    button.addEventListener("click", () => {
        const display = document.getElementById("display");
        display.value += button.value;
    });
});

document.querySelectorAll(".operator").forEach(button => {
    button.addEventListener("click", () => {
        const display = document.getElementById("display");
        display.value += " " + button.dataset.operator + " ";
    });
});

document.getElementById("clear").addEventListener("click", () => {
    document.getElementById("display").value = "";
});

document.getElementById("calculate").addEventListener("click", async () => {
    const display = document.getElementById("display");
    const expression = display.value.split(" ");
    const n1 = parseFloat(expression[0]);
    const op = expression[1];
    const n2 = parseFloat(expression[2]);
    if (!isNaN(n1) && !isNaN(n2) && op) {
        try {
            const response = await fetch(`/calculate?num1=${n1}&num2=${n2}&operator=${op}`);
            const data = await response.json();
            display.value = data.result;
        } catch (error) {
            console.error('Error:', error);
            alert('An error occurred while processing the calculation.');
        }
    } else {
        alert('Please enter a valid expression.');
    }
});

document.addEventListener("keydown", event => {
    const key = event.key;
    if (!isNaN(key) || key === "+" || key === "-" || key === "*" || key === "/" || key === ".") {
        const display = document.getElementById("display");
        display.value += key;
    } else if (key === "Enter") {
        document.getElementById("calculate").click();
    } else if (key === "Backspace") {
        const display = document.getElementById("display");
        display.value = display.value.slice(0, -1);
    }
});