const searchImageBoxes = [];
const searchImageContainer = document.querySelector('.searchImageContainer');
const searchLocation = document.getElementById('searchLocation');
const searchStart = document.getElementById('searchStart');
const searchEnd = document.getElementById('searchEnd');
const searchLocationContainer = document.querySelector('.searchLocationContainer');
const searchStartContainer = document.querySelector('.searchStartContainer');
const searchEndContainer = document.querySelector('.searchEndContainer');
let viewSearch = null;
const countryNameContainer = document.querySelector('.countryNameContainer');
const locationTagContainer = document.querySelector('.locationTagContainer');
const countryImages = searchLocationContainer.querySelectorAll('.countryImageBox');
let selectLocationTag = null;
const now = new Date();
const dateTypeYearNow = now.getFullYear();
const dateTypeMonthNow = now.getMonth() + 1;
const locationMap = new Map([
    ["한국", [
        "서울", "부산", "제주", "인천", "경주",
        "대구", "대전", "강남", "수원", "안동"
    ]],
    ["일본", [
        "도쿄", "교토", "오사카", "홋카이도", "나라",
        "히로시마", "후쿠오카", "나고야", "오키나와", "고베"
    ]],
    ["중국", [
        "베이징", "상하이", "시안", "청두", "광저우",
        "선전", "항저우", "쑤저우", "라싸", "구이린"
    ]],
    ["미국", [
        "뉴욕", "로스앤젤레스", "샌프란시스코", "라스베이거스", "시카고",
        "마이애미", "워싱턴 D.C.", "시애틀", "올랜도", "보스턴"
    ]],
    ["유럽", [
        "파리", "로마", "바르셀로나", "런던", "암스테르담",
        "베를린", "프라하", "빈", "베네치아", "부다페스트"
    ]]
]);

for (const countryImage of countryImages) {
    countryImage.addEventListener('click', (e)=> {
        locationTagContainer.innerHTML = '';
        countryNameContainer.innerText = countryImage.id;
        for (const location of locationMap.get(countryImage.id)) {
            const locationTag = document.createElement('div')
            locationTag.classList.add('locationTagBox')
            locationTag.innerText = location
            locationTagContainer.appendChild(locationTag);

            locationTag.addEventListener('click', (e)=> {
                if (selectLocationTag != null) {
                    selectLocationTag.style.backgroundColor = 'white';
                    selectLocationTag.style.color = 'black';
                }
                selectLocationTag = locationTag;
                selectLocationTag.style.backgroundColor = '#333333';
                selectLocationTag.style.color = 'white';
            })
        }
        scrollDown()
    })
}

searchLocation.addEventListener('click', (e) => {
    if (viewSearch != null) viewSearch.style.display = 'none';
    searchLocationContainer.style.display = 'flex';
    viewSearch = searchLocationContainer;
    scrollDown()
})
searchStart.addEventListener('click', (e) => {
    if (viewSearch != null) viewSearch.style.display = 'none';
    searchStartContainer.style.display = 'flex';
    viewSearch = searchStartContainer;
    scrollDown()
})
searchEnd.addEventListener('click', (e) => {
    if (viewSearch != null) viewSearch.style.display = 'none';
    searchEndContainer.style.display = 'flex';
    viewSearch = searchEndContainer;
    scrollDown()
})

function scrollDown() {
    window.scrollTo({
        top: 1000, behavior:"smooth"
    })
}

function addDateNum(searchDateContainer, dateTypeYearNow, dateTypeMonthNow) {
    const monthTextContainer = searchDateContainer.querySelector('.monthTextContainer');
    const dateContainer = searchDateContainer.querySelector('.dateContainer');
    const maxDaysInMonth = getMaxDaysInMonth(dateTypeYearNow, dateTypeMonthNow);
    const dayOfWeek = getDayOfWeek(dateTypeYearNow, dateTypeMonthNow)
    monthTextContainer.innerText = `${dateTypeYearNow}년 ${dateTypeMonthNow}월`;

    for (const i of dayOfWeek) {

    }
}

function addDateTextBox(text, dateContainer) {
    const dateTextBox = document.createElement('.dateTextBox');
    dateTextBox.innerText = text;
    dateTextBox.addEventListener('click', (e)=> {

    })
}

function getMaxDaysInMonth(year, month) {
    return new Date(year, month + 1, 0).getDate();
}

function getDayOfWeek(year, month, day = 1) {
    const date = new Date(year, month, day);
    return date.getDay();
}

function setImageHeight(target, sourceElement) {
    const sourceHeight = sourceElement.offsetHeight;
    target.style.height = sourceHeight + "px";
    searchImageBoxes.push(target);
}

function addSearchImage(num, sourceElement) {
    for (let i = 0; i < num; i++) {
        const searchImageBox = document.createElement('div');
        searchImageBox.classList.add("searchImageBox");
        searchImageBox.innerText = "searchImage"
        setImageHeight(searchImageBox, sourceElement)
        searchImageContainer.appendChild(searchImageBox);
    }
}



