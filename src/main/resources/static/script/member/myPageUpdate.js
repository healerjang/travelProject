const updateRouters = document.querySelectorAll(".personalInformationBoxContentUpdateRouter");
const personalInformationNormalActive = document.querySelector(".personalInformationNormalActive");
const personalInformationUpdateActive = document.querySelector(".personalInformationUpdateActive");
const updateBoxContentHeader = personalInformationUpdateActive.querySelector(".updateBoxContentHeader");
const updateInputHeader = personalInformationUpdateActive.querySelector(".updateInputHeader");
const updateBoxContentText = personalInformationUpdateActive.querySelector(".updateBoxContentText");
const updateHeaderBack = personalInformationUpdateActive.querySelector(".updateHeaderBack");
const cancelButton = personalInformationUpdateActive.querySelector(".cancelButton");
const updateInput = personalInformationUpdateActive.querySelector(".updateInput");
const updateDuplicateLoading = personalInformationUpdateActive.querySelector(".updateDuplicateLoading");
const updateDuplicateCheck = personalInformationUpdateActive.querySelector(".updateDuplicateCheck");
const updateDuplicateCross = personalInformationUpdateActive.querySelector(".updateDuplicateCross");
const updateDuplicateGuide = personalInformationUpdateActive.querySelector(".updateDuplicateGuide");
const saveButton = personalInformationUpdateActive.querySelector(".saveButton");
let activeUpdateKeyword = null;
let activeUpdatePage = false;
let updateCheck = false;
let activeCallId = 0;
const updateKeyWordDict = new Map([
    ["아이디", "id"],
    ["이름", "name"],
    ["이메일", "email"],
    ["전화번호", "phone"]
]);

for (const updateRouter of updateRouters) {
    updateRouter.addEventListener('click', (e) => {
        updateRouterClick(updateRouter);
    })
}

updateHeaderBack.addEventListener('click', (e) => {
    activeNormal()
})
cancelButton.addEventListener('click', (e) => {
    activeNormal()
})

updateInput.addEventListener('input', (e) => {
    if (activeUpdatePage) {
        activeCallId++;
        const currentCallId = activeCallId;
        duplicateCheckToKeyword(updateKeyWordDict.get(activeUpdateKeyword), currentCallId);
    }
})

function hideUpdateGuides() {
    updateDuplicateGuide.style.display = "none";
    updateDuplicateCheck.style.display = "none";
    updateDuplicateCross.style.display = "none";
    updateDuplicateLoading.style.display = "none";
}

function updateRouterClick(updateRouter) {
    personalInformationNormalActive.style.display = "none";
    personalInformationUpdateActive.style.display = "block";

    const routerParent = updateRouter.parentNode;
    activeUpdateKeyword = routerParent.querySelector(".personalInformationBoxContentHeader").innerText;
    updateBoxContentHeader.innerText = `기존 ${activeUpdateKeyword}`;
    updateInputHeader.innerText = `변경 후 ${activeUpdateKeyword}`;
    updateBoxContentText.innerText = routerParent.querySelector(".personalInformationBoxContentText").innerText;

    hideUpdateGuides();
    updateCheck = false;
    activeUpdatePage = true;
}

function activeNormal() {
    personalInformationUpdateActive.style.display = "none";
    personalInformationNormalActive.style.display = "block";
    updateCheck = false;

    updateInput.innerText = "";
    hideUpdateGuides();
    activeUpdatePage = false;
}

saveButton.addEventListener('click', (e) => {
    const keyword = updateKeyWordDict.get(activeUpdateKeyword);
    if (updateCheck) {
        const trimmedInput = updateInput.innerText.trim();
        updateMember(keyword, trimmedInput).then(success => {
            if (success) {
                document.getElementById(`${keyword}View`).innerText = trimmedInput;
                activeNormal();
            } else {
                alert("사용자의 데이터가 이미 수정되었거나 없는 사용자입니다.")
            }
        })
    }
})

function delay(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

async function duplicateCheckToKeyword(keyword, id) {
    hideUpdateGuides();
    updateDuplicateLoading.style.display = "block";

    await delay(1000)

    const inputData = updateInput.innerText;
    if (inputData !== "") {
        const response = await axios.get(`/member/update/${keyword}/${encodeURIComponent(inputData)}`);
        const check = response.data;
        if (activeCallId === id) {
            if (check) {
                updateDuplicateLoading.style.display = "none";
                updateDuplicateCheck.style.display = "block";
                updateDuplicateGuide.style.display = "block";
                updateDuplicateGuide.innerText = `해당 ${keyword}는 사용할 수 있습니다.`
                updateCheck = true;
            } else {
                updateDuplicateLoading.style.display = "none";
                updateDuplicateCross.style.display = "block";
                updateDuplicateGuide.style.display = "block";
                updateDuplicateGuide.innerText = `해당 ${keyword}는 사용할 수 없습니다.`
                updateCheck = false;
            }
        }
    }
}

async function updateMember(keyword, inputData) {
    const response = await axios.put(`/member/update/${keyword}`, inputData, {
        headers: {'Content-Type': 'application/json'}
    });
    return response.data;
}