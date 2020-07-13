// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/*
let navBar = document.querySelector(".nav-bar#top-nav");

function scrollEvent(scrollPos) {
  let elementAtPageCenter = document.elementFromPoint(window.innerWidth/2,window.innerHeight*2/5);
  let block = elementAtPageCenter;
  if (scrollPos === 0) {
    block = null;
  }
  while (block && !block.classList.contains("content-block")) {
    block = block.parentElement;
  }


  let current = navBar.querySelector(".active");
  if (current != null) {
    current.classList.remove("active");
  }

  if (block) {
    let navID = "nav-" + block.id;
    let activeNavItem = navBar.querySelector("#"+navID);
    activeNavItem.className += " active";
  }
}

//requestAnimationFrame is used to limit the frequency of function calls on every scroll event
let lastScrollPos = 0;
let ticking = false;
window.addEventListener('scroll', function(e) {
  lastScrollPos = window.scrollY;

  if (!ticking) {
    window.requestAnimationFrame(function() {
      scrollEvent(lastScrollPos);
      ticking = false;
      */
function loadUserInfo () {
  const userInfoElem = document.getElementById('userInfo');
  userInfoElem.innerHTML = '';
  const pathName = window.location.pathname;
  const request = '/profile?userID=' + (pathName.split('/')[0]);
  fetch(request).then(handleFetchErrors).
    then(response => response.json()).then((userAccount) => {
      for (memberKey in userAccount) {
        userInfoElem.appendChild(createListElement(userFieldToString(userAccount, memberKey)));
      }
    })
};

function handleFetchErrors(response) {
  if(!response.ok) throw Error(response.statusText);
  return response;
}

function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}

/* TODO: make sure this syntax actually works
function useFieldToString(userAccount, memberKey) {
  const stringTypeFieldList
}
*/
