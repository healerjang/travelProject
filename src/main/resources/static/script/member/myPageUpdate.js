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
let activeUpdateKeyword = null;
let activeDuplicateIcon = null;
const updateKeyWordDict = new Map([
    ["아이디", "id"],
    ["이름", "name"],
    ["이메일", "email"],
    ["전화번호", "phone"]
]);

for (const updateRouter of updateRouters) {
    updateRouter.addEventListener('click', (e)=> {
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
    duplicateCheck();
})

function updateRouterClick(updateRouter) {
    personalInformationNormalActive.style.display = "none";
    personalInformationUpdateActive.style.display = "block";

    const routerParent = updateRouter.parentNode;
    activeUpdateKeyword = routerParent.querySelector(".personalInformationBoxContentHeader").innerText;
    updateBoxContentHeader.innerText = `기존 ${activeUpdateKeyword}`;
    updateInputHeader.innerText = `변경 후 ${activeUpdateKeyword}`;
    updateBoxContentText.innerText = routerParent.querySelector(".personalInformationBoxContentText").innerText;
}

function activeNormal() {
    personalInformationUpdateActive.style.display = "none";
    personalInformationNormalActive.style.display = "block";
}

function duplicateCheck() {
    if (activeDuplicateIcon != null) activeDuplicateIcon.style.display = "none";
    updateDuplicateLoading.style.display = "block";
    updateDuplicateGuide.style.display = "none";
    const keyword = updateKeyWordDict.get(activeUpdateKeyword);
    console.log(keyword)
    duplicateCheckToKeyword(keyword, updateInput.value).then(duplicate => {
        console.log(duplicate)
        if (duplicate) {
            updateDuplicateLoading.style.display = "none";
            activeDuplicateIcon = updateDuplicateCheck;
            activeDuplicateIcon.style.display = "block";
            updateDuplicateGuide.style.display = "block";
            updateDuplicateGuide.innerText = `해당 ${keyword}는 사용할 수 있습니다.`
        }
        else {
            updateDuplicateLoading.style.display = "none";
            activeDuplicateIcon = updateDuplicateCross;
            activeDuplicateIcon.style.display = "block";
            updateDuplicateGuide.style.display = "block";
            updateDuplicateGuide.innerText = `해당 ${keyword}는 사용할 수 없습니다.`
        }
    })
}

async function duplicateCheckToKeyword(keyword, inputData) {
    const response = await axios.get(`/member/update/${keyword}/${encodeURIComponent(inputData)}`);
    return response.data;
}