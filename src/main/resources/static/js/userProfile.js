
$(document).ready(function () {
  $(".user_update_form").hide();
});


function changeUserDetails() {

  $(".user_update_form").toggle();
  $(".user_details").toggle();


}


// ------------ collapsing comment section code -- starting !!!!!!!!!!!!!!!!!

var tm = document.getElementsByClassName("collapseComment");
var tm2 = document.getElementsByClassName("collapseExamples");

for (let i1 = 0; i1 < tm.length; i1++) {
  tm[i1].onclick = function () {
    $(tm2[i1]).fadeToggle();
  }
}
// for href tag
var tm3 = document.getElementsByClassName("collapseComment2");

for (let i1 = 0; i1 < tm3.length; i1++) {
  tm3[i1].onclick = function () {

    $(tm2[i1]).fadeToggle();

  }
}


// ------------ collapsing comment section code -- Ending !!!!!!!!!!!!!!!!!!!!!!

// validating comment input box ==>> Startng -- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
var commentTextArea = document.getElementsByClassName("commentTextArea");
var commentButton = document.getElementsByClassName("commentButton");


for (let i1 = 0; i1 < commentTextArea.length; i1++) {
  $(commentTextArea[i1]).on('input change', function () {
    if ($(commentTextArea[i1]).val() != '' && jQuery.trim($(commentTextArea[i1]).val()) != '') {
      $(commentButton[i1]).prop('disabled', false);
    } else {
      $(commentButton[i1]).prop('disabled', true);
    }
  });
}
// validating comment input box ==>> Ending -!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

// like button !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
var likeButton = document.getElementsByClassName("likeButton");
var iconLikeButton = document.getElementsByClassName("iconLikeButton");

for (let i1 = 0; i1 < likeButton.length; i1++) {
  $(likeButton[i1]).click(function () {
    $(this).css({ "color": "blue", "box-shadow": "3px 3px #888888" });
  })
}

// read more less code !!!!!!!!!!!!!!!!!!
$(document).ready(function () {
  var maxLength = 300;
  $(".show-read-more").each(function () {

    var data = $(this).text();

    var tempDivElement = document.createElement("span");
    // Set the HTML content with the given value
    tempDivElement.innerHTML = data;

    var myStr = tempDivElement.textContent;

    if ($.trim(myStr).length > maxLength) {
      var newStr = myStr.substring(0, maxLength);
      var removedStr = myStr.substring(maxLength, $.trim(myStr).length);
      $(this).empty().html(newStr);
      $(this).append(' <a href="javascript:void(0);" class="read-more">read more...</a>');
      $(this).append('<span class="more-text">' + removedStr + '</span>');
    }
  });
  $(".read-more").click(function () {
    $(this).siblings(".more-text").contents().unwrap();
    $(this).remove();
  });
});


// file upload 

const dropArea = document.querySelector(".drag-image"),
  dragText = dropArea.querySelector("h6"),
  button = dropArea.querySelector("button"),
  input = dropArea.querySelector("input");
let file;
test_image = document.querySelector(".test-image"),

  button.onclick = () => {
    input.click();
  }

input.addEventListener("change", function () {

  file = this.files[0];
  dropArea.classList.add("active");
  viewfile();
});

dropArea.addEventListener("dragover", (event) => {
  event.preventDefault();
  dropArea.classList.add("active");
  dragText.textContent = "Release to Upload File";
});


dropArea.addEventListener("dragleave", () => {
  dropArea.classList.remove("active");
  dragText.textContent = "Drag & Drop to Upload File";
});

dropArea.addEventListener("drop", (event) => {
  event.preventDefault();

  file = event.dataTransfer.files[0];
  viewfile();
});

function viewfile() {
  let fileType = file.type;
  let validExtensions = ["image/jpeg", "image/jpg", "image/png", "image/svg+xml"];
  if (validExtensions.includes(fileType)) {
    let fileReader = new FileReader();
    fileReader.onload = () => {
      let fileURL = fileReader.result;
      let imgTag = `<img src="${fileURL}" alt="image">`;
      test_image.innerHTML = imgTag;
    }
    fileReader.readAsDataURL(file);
  } else {
    alert("This is not an Image File!");
    dropArea.classList.remove("active");
    dragText.textContent = "Drag & Drop to Upload File";
  }
}

// disbled button code for change profile pictures
var profileInput = document.getElementsByClassName("profileInput");
var profileInputButton = document.getElementsByClassName("profileInputButton");

$(profileInput).on('input change', function () {
  $(profileInputButton).prop('disabled', false);
});

// file upload Ending

