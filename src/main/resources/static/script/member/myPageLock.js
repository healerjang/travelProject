const inputTextAreas = document.querySelectorAll(".inputTextArea");

for(const inputTextArea of inputTextAreas) {
    inputTextArea.addEventListener("click", (e)=> {
        clickInputTextArea(inputTextArea);
    })
}

function clickInputTextArea(inputTextArea) {
    if (inputTextArea.dataset.firstInput === "true") {
        inputTextArea.innerText = "";
        inputTextArea.dataset.firstInput = "false";
    }
}