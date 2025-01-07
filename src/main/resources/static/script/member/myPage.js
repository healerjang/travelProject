const homeHeader = document.getElementById("home");
const personalInformationHeader = document.getElementById("personalInformation");
const lockHeader = document.getElementById("lock");
const homeContainer = document.querySelector(".homeContainer");
const personalInformationContainer = document.querySelector('.personalInformationContainer');
const lockContainer = document.querySelector(".lockContainer");
const homeContentPersonalInformationBox = homeContainer.querySelector(".homeContentPersonalInformationBox");
const homeContentLockBox = homeContainer.querySelector(".homeContentLockBox");
const headerMainIcon = document.querySelector(".header__main");
const memberTag = document.getElementById("member");
const shoppingCartTag = document.getElementById("shoppingCart");
const reservationTag = document.getElementById("reservation");
let activeHeader = homeHeader;
let openContainer = homeContainer;

homeHeader.addEventListener('click', (e) => {
    activeHomeHeader();
})

personalInformationHeader.addEventListener('click', (e) => {
    activePersonalInformationHeader();
})

lockHeader.addEventListener('click', (e) => {
    activeLockHeader();
})

homeContentPersonalInformationBox.addEventListener('click', (e) => {
    activePersonalInformationHeader();
})

homeContentLockBox.addEventListener('click', (e) => {
    activeLockHeader();
})

headerMainIcon.addEventListener('click', (e)=> {
    window.location.href = '../mainPage?';
})

memberTag.addEventListener('click', (e)=> {
    window.location.href = '/myPage?';
})

shoppingCartTag.addEventListener('click', (e)=> {
    window.location.href = 'shoppingCart?';
})

reservationTag.addEventListener('click', (e)=> {
    window.location.href = 'reservation?';
})

function activeHomeHeader() {
    activeHeader.style.backgroundColor = "white";
    activeHeader = homeHeader;
    activeHeader.style.backgroundColor = "#a8a8a8";
    openContainer.style.display = "none";
    openContainer = homeContainer;
    openContainer.style.display = "block";
}

function activePersonalInformationHeader() {
    activeHeader.style.backgroundColor = "white";
    activeHeader = personalInformationHeader;
    activeHeader.style.backgroundColor = "#a8a8a8";
    openContainer.style.display = "none";
    openContainer = personalInformationContainer;
    openContainer.style.display = "block";
}

function activeLockHeader() {
    activeHeader.style.backgroundColor = "white";
    activeHeader = lockHeader;
    activeHeader.style.backgroundColor = "#a8a8a8";
    openContainer.style.display = "none";
    openContainer = lockContainer;
    openContainer.style.display = "block";
}
