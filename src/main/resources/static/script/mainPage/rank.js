const rankImageBoxes = [];
const rankImageContainer = document.querySelector('.rankImageContainer');
const rankImageContainerLeftArrow = document.querySelector('.rankImageContainerLeftArrow');
const rankImageContainerRightArrow = document.querySelector('.rankImageContainerRightArrow');
const rankImageContainerWidth = rankImageContainer.offsetWidth;
const centerY = rankImageContainer.offsetHeight / 2;
let canMoveToRightRankImageBoxes = 0;
let canMoveToLeftRankImageBoxes = 0;
let rankImageBoxWidth = null;
let rankImageBoxHeight = null;
let margin = null;

function getRankImage(num) {
    for (let i = 0; i < num; i++) {
        const rankImageBox = document.createElement('div');
        rankImageBox.classList.add("rankImageBox");
        rankImageBoxes.push(rankImageBox);
        rankImageContainer.appendChild(rankImageBox);
        addRankImage(rankImageBox);
    }
}

function addRankImage(rankImageBox) {
    if (rankImageBoxHeight == null) rankImageBoxHeight = rankImageBox.offsetHeight;
    if (rankImageBoxWidth == null) rankImageBoxWidth = rankImageBox.offsetWidth;

    if (margin == null) margin = (rankImageContainerWidth - (rankImageBoxWidth * 3)) / 4;

    const top = centerY - (rankImageBoxHeight / 2);
    const left = (margin + (rankImageBoxes.length - 1) * (margin + rankImageBoxWidth))
    rankImageBox.style.left = `${left}px`;
    rankImageBox.style.top = `${top}px`;
}

function moveToRightRankImageBoxes() {
    if (canMoveToRightRankImageBoxes < 7) {
        for (const rankImageBox of rankImageBoxes) {
            const leftPosition = parseFloat(rankImageBox.style.left) - (margin + rankImageBoxWidth);
            rankImageBox.style.left = `${leftPosition}px`;
        }
        canMoveToRightRankImageBoxes ++;
        canMoveToLeftRankImageBoxes ++;
    }
}

function moveToLeftRankImageBoxes() {
    if (canMoveToLeftRankImageBoxes > 0) {
        for (const rankImageBox of rankImageBoxes) {
            const leftPosition = parseFloat(rankImageBox.style.left) + (margin + rankImageBoxWidth);
            rankImageBox.style.left = `${leftPosition}px`;
        }
        canMoveToRightRankImageBoxes --;
        canMoveToLeftRankImageBoxes --;
    }
}

rankImageContainerRightArrow.addEventListener('click', e=> {
    moveToRightRankImageBoxes()
})

rankImageContainerLeftArrow.addEventListener('click', e=> {
    moveToLeftRankImageBoxes()
})

getRankImage(10);