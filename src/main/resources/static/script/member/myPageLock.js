const inputTextAreas = document.querySelectorAll(".inputTextArea");
const oldPassword = document.getElementById("oldPassword");
const newPassword = document.getElementById("newPassword");
const passwordCheck = document.getElementById("passwordCheck");
const changeButton = document.querySelector(".changeButton");

for(const inputTextArea of inputTextAreas) {
    inputTextArea.addEventListener("focus", (e)=> {
        clickInputTextArea(inputTextArea);
    })
}

changeButton.addEventListener("click", (e)=> {
    changePassword(oldPassword.innerText, newPassword.innerText, passwordCheck.innerText).then(success => {
        if (success) {
            alert("비밀번호 변경 완료")
            clearPassword();
        }
        else {
            alert("비밀번호 변경 실패")
            clearPassword();
        }
    })
})

function clearPassword() {
    oldPassword.innerText = "";
    newPassword.innerText = "";
    passwordCheck.innerText = "";
}

function clickInputTextArea(inputTextArea) {
    if (inputTextArea.dataset.firstInput === "true") {
        inputTextArea.innerText = "";
        inputTextArea.dataset.firstInput = "false";
    }
}

async function changePassword(oldPassword, newPassword, checkPassword) {
    if (newPassword !== checkPassword) {
        alert("변경 비밀번호가 동일하지 않습니다.")
        return false;
    }
    const requestData = {
        oldPassword: oldPassword,
        newPassword: newPassword
    }
    const response = await axios.put(`/member/update/password`, requestData, {
        headers: { 'Content-Type': 'application/json' }
    });
    return response.data;
}