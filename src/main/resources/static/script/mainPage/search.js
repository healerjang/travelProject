const searchLocation = document.getElementById('searchLocation');
const searchStart = document.getElementById('searchStart');
const searchEnd = document.getElementById('searchEnd');
const searchLocationContainer = document.querySelector('.searchLocationContainer');
const searchStartContainer = document.querySelector('.searchStartContainer');
const searchEndContainer = document.querySelector('.searchEndContainer');
let viewSearch = null;
const countryNameContainer = document.querySelector('.countryNameContainer');
const locationTagContainer = document.querySelector('.locationTagContainer');
const countryImageContainer = document.querySelector('.countryImageContainer');
let selectLocationTag = null;
const now = new Date();
let dateTypeYearNow = now.getFullYear();
let dateTypeMonthNow = now.getMonth() + 1;
const selectStart = { current: null };
const selectEnd = { current: null };
let startContainSetting = false;
let endContainSetting = false;
const searchImageIcon = document.querySelector('.searchIconBox').querySelector('img');
const imageContainer = document.querySelector('.imageContainer');
let startDate = null;
let endDate = null;
let locationNo = null;
let isAddImage = false;
let page = 1;

document.addEventListener('click', (e)=> {
    if (!e.target.closest('.searchContainer')) {
        if (viewSearch != null) {
            viewSearch.style.display = 'none';
            viewSearch = null;
        }
    }
})

function isImageValid(url, collBack) {
    const img = new Image();
    img.onload = () => collBack(true);
    img.onerror = () => collBack(false);
    img.src = url;
}

function addCountryImage(country) {
    const imagePath = `/images/country/${country}.png`;
    const countryImageBox = document.createElement("div");
    countryImageBox.classList.add("countryImageBox");
    isImageValid(imagePath, (isValid) => {
        if (isValid) countryImageBox.style.backgroundImage = `url('${imagePath}')`
        else countryImageBox.style.backgroundImage = `url('/images/noneImage.png')`
    })

    countryImageBox.name = country;
    addCountryImageClickListener(countryImageBox);
    countryImageContainer.appendChild(countryImageBox);
}

function addCityTag(locationDTO) {
    const locationTag = document.createElement('div')
    locationTag.classList.add('locationTagBox')
    locationTag.innerText = locationDTO.city;
    locationTag.loactionNo = locationDTO.locationNo;
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

searchLocation.addEventListener('click', (e) => {
    if (viewSearch != null) viewSearch.style.display = 'none';
    searchLocationContainer.style.display = 'flex';
    viewSearch = searchLocationContainer;
})
searchStart.addEventListener('click', (e) => {
    if (viewSearch != null) viewSearch.style.display = 'none';
    searchStartContainer.style.display = 'flex';
    viewSearch = searchStartContainer;
    if (!startContainSetting) {
        addDateNum(searchStartContainer, dateTypeYearNow, dateTypeMonthNow, selectStart);
        startContainSetting = true;
    }
})
searchEnd.addEventListener('click', (e) => {
    if (viewSearch != null) viewSearch.style.display = 'none';
    searchEndContainer.style.display = 'flex';
    viewSearch = searchEndContainer;
    if (!endContainSetting) {
        addDateNum(searchEndContainer, dateTypeYearNow, dateTypeMonthNow, selectEnd);
        endContainSetting = true;
    }
})

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

function isSearch() {
    return locationNo != null || startDate != null || endDate != null;
}

searchImageIcon.addEventListener('click', (e)=> {
    if (searchValidCheck()) {
        locationNo = null;
        startDate = null;
        endDate = null;
        page = 1;

        if (selectLocationTag != null) locationNo = selectLocationTag.loactionNo;
        if (selectStart.current != null) startDate = `${selectStart.current.year}-${String(selectStart.current.month).padStart(2, '0')}-${String(selectStart.current.day).padStart(2, '0')}`;
        if (selectEnd.current != null) endDate = `${selectEnd.current.year}-${String(selectEnd.current.month).padStart(2, '0')}-${String(selectEnd.current.day).padStart(2, '0')}`;

        printImageContainer(page, 10, locationNo, startDate, endDate)
        resetSearch();
    }
    else searchError('출발일은 도착일보다 빨라야합니다.')
})

function resetSearch() {
    if (viewSearch != null) viewSearch.style.display = 'none';
    if (selectLocationTag != null) {
        selectLocationTag.style.backgroundColor = 'white';
        selectLocationTag.style.color = 'black';
        selectLocationTag = null;
    }
    if (selectStart.current != null) {
        selectStart.current.style.backgroundColor = 'white';
        selectStart.current.style.color = 'black';
        selectStart.current = null;
    }
    if (selectEnd.current != null) {
        selectEnd.current.style.backgroundColor = 'white';
        selectEnd.current.style.color = 'black';
        selectEnd.current = null;
    }
}

function searchValidCheck() {
    if (selectStart.current == null || selectEnd.current == null) {
        return true;
    }

    const startDate = new Date(selectStart.current.year, selectStart.current.month - 1, selectStart.current.day);
    const endDate = new Date(selectEnd.current.year, selectEnd.current.month - 1, selectEnd.current.day);

    return startDate <= endDate;
}

function addImage(name, imagePath, productNo) {
    const imageBox = document.createElement('div');
    const imageBoxTitle = document.createElement('div');
    const imageBoxContainer = document.createElement('div');
    imageBox.classList.add('imageBox');
    imageBox.style.backgroundImage = `url('../images/${imagePath}')`
    imageBoxTitle.classList.add('imageBoxTitle');
    imageBoxTitle.innerText = name;
    imageBoxContainer.classList.add('imageBoxContainer')

    imageBox.appendChild(imageBoxTitle);
    imageBoxContainer.appendChild(imageBox);
    imageContainer.appendChild(imageBoxContainer);

    imageBox.addEventListener('click', (e) => {
        console.log(productNo);
    })
}

function searchError(error) {
    alert(error);
}

function getContentToScrollDown() {
    if (!searchTotal || !isAddImage) return false
    addImageContainer(++page, 10, locationNo, startDate, endDate);
}

window.addEventListener('scroll', () => {
    if (window.innerHeight + window.scrollY >= document.documentElement.scrollHeight ) {
        console.log("스크롤이벤트 동작중")
        getContentToScrollDown();
    }
})




