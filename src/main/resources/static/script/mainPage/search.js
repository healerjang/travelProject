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
let dateTypeYearNow = now.getFullYear();
let dateTypeMonthNow = now.getMonth() + 1;
const selectStart = { current: null };
const selectEnd = { current: null };
let startMonthUpdateValue = 0;
let endMonthUpdateValue = 0;
const startContainLeftImage = searchStartContainer.querySelector('.monthTextContainerLeftArrow');
const startContainRightImage = searchStartContainer.querySelector('.monthTextContainerRightArrow');
const endContainLeftImage = searchEndContainer.querySelector('.monthTextContainerLeftArrow');
const endContainRightImage = searchEndContainer.querySelector('.monthTextContainerRightArrow');
let startContainSetting = false;
let endContainSetting = false;
const searchImageIcon = document.querySelector('.searchIconBox').querySelector('img');
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
    if (!startContainSetting) {
        addDateNum(searchStartContainer, dateTypeYearNow, dateTypeMonthNow, selectStart);
        startContainSetting = true;
    }
    scrollDown()
})
searchEnd.addEventListener('click', (e) => {
    if (viewSearch != null) viewSearch.style.display = 'none';
    searchEndContainer.style.display = 'flex';
    viewSearch = searchEndContainer;
    if (!endContainSetting) {
        addDateNum(searchEndContainer, dateTypeYearNow, dateTypeMonthNow, selectEnd);
        endContainSetting = true;
    }
    scrollDown()
})

startContainLeftImage.addEventListener('click', (e) => {
    if (startMonthUpdateValue > 0) {
        startMonthUpdateValue--;
        const dateTypeYear = Math.trunc(startMonthUpdateValue / 12)
        const dateTypeMonth = startMonthUpdateValue % 12;
        addDateNum(searchStartContainer, dateTypeYearNow + dateTypeYear, dateTypeMonthNow + dateTypeMonth, selectStart)
    }
})

startContainRightImage.addEventListener('click', (e) => {
    startMonthUpdateValue ++;
    const dateTypeYear = Math.trunc(startMonthUpdateValue / 12)
    const dateTypeMonth = startMonthUpdateValue % 12;
    addDateNum(searchStartContainer, dateTypeYearNow + dateTypeYear, dateTypeMonthNow + dateTypeMonth, selectStart)
})

endContainLeftImage.addEventListener('click', (e) => {
    if (endMonthUpdateValue > 0) {
        endMonthUpdateValue--;
        const dateTypeYear = Math.trunc(endMonthUpdateValue / 12)
        const dateTypeMonth = endMonthUpdateValue % 12;
        addDateNum(searchEndContainer, dateTypeYearNow + dateTypeYear, dateTypeMonthNow + dateTypeMonth, selectEnd)
    }
})

endContainRightImage.addEventListener('click', (e) => {
    endMonthUpdateValue++;
    const dateTypeYear = Math.trunc(endMonthUpdateValue / 12)
    const dateTypeMonth = endMonthUpdateValue % 12;
    addDateNum(searchEndContainer, dateTypeYearNow + dateTypeYear, dateTypeMonthNow + dateTypeMonth, selectEnd)
})

function scrollDown() {
    window.scrollTo({
        top: 1000, behavior:"smooth"
    })
}

function addDateNum(searchDateContainer, dateTypeYear, dateTypeMonth, selectDate) {
    const monthTextContainer = searchDateContainer.querySelector('.monthTextContainer');
    const dateContainer = searchDateContainer.querySelector('.dateContainer');
    const maxDaysInMonth = getMaxDaysInMonth(dateTypeYear, dateTypeMonth);
    const dayOfWeek = getDayOfWeek(dateTypeYear, dateTypeMonth)
    monthTextContainer.querySelector('.monthText').innerText = `${dateTypeYear}년 ${dateTypeMonth}월`;
    const setDateTextNum = dateContainer.querySelectorAll('.dateNumBox');

    for (const item of setDateTextNum) {
        dateContainer.removeChild(item);
    }

    for (let i = 0; i < dayOfWeek; i++) {
        addDateTextBox('', dateContainer, selectDate, dateTypeYear, dateTypeMonth);
    }
    for (let i = 1; i <= maxDaysInMonth; i++) {
        addDateTextBox(i, dateContainer, selectDate, dateTypeYear, dateTypeMonth);
    }
}

function addDateTextBox(text, dateContainer, selectDateObj, dateTypeYear, dateTypeMonth) {
    const dateNumBox = document.createElement('div');
    dateNumBox.classList.add('dateNumBox')
    dateNumBox.innerText = text;
    dateNumBox.addEventListener('click', (e)=> {
        if (text !== '') {
            if (selectDateObj.current != null) {
                selectDateObj.current.style.backgroundColor = 'white';
                selectDateObj.current.style.color = 'black';
            }
            dateNumBox.year = dateTypeYear;
            dateNumBox.month = dateTypeMonth;
            dateNumBox.day = parseInt(text);
            selectDateObj.current = dateNumBox;
            selectDateObj.current.style.backgroundColor = '#333333';
            selectDateObj.current.style.color = 'white';
        }
    })
    dateContainer.appendChild(dateNumBox);
}

function getMaxDaysInMonth(year, month) {
    return new Date(year, month + 1, 0).getDate();
}

function getDayOfWeek(year, month, day = 1) {
    const date = new Date(year, month, day);
    return date.getDay();
}

searchImageIcon.addEventListener('click', (e)=> {
    if (searchValidCheck()) {
        console.log(selectLocationTag.innerText);
        console.log(`${selectStart.current.year}년 ${selectStart.current.month}월 ${selectStart.current.day}일`)
        console.log(`${selectEnd.current.year}년 ${selectEnd.current.month}월 ${selectEnd.current.day}일`)
    }
    else searchError('출발일은 도착일보다 빨라야합니다.')
})

function searchValidCheck() {
    if (selectStart != null && selectEnd != null) {
        let startNum = selectStart.current.year * 365;
        let endNum = selectEnd.current.year * 365;
        startNum += selectStart.current.month * 30;
        endNum += selectEnd.current.month * 30;
        startNum += selectStart.current.day;
        endNum += selectEnd.current.day;
        console.log(startNum, endNum)
        if (startNum > endNum) return false;
    }
    return true;
}

function searchError(error) {
    alert(error);
}




