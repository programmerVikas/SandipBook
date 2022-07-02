// for showing the name of file in popup modal while posting



const actualBtn = document.getElementById('actual-btn');

const fileChosen = document.getElementById('file-chosen');

actualBtn.addEventListener('change', function () {
  fileChosen.textContent = this.files[0].name

})

// for showing file in popup modal while posting 

function img_pathUrl(input) {

  document.getElementById('pBar').style.display = "block";

  // this is just fort loading the file time, no work specific
  let formData = new FormData();
  formData.append("file", input.files[0]);

  document.getElementById('pBar').value = '30';

  if (input.files[0].type.match('image.*')) {
    $('#pdf_url')[0].src = "";
    $('#img_url')[0].src = (window.URL ? URL : webkitURL).createObjectURL(input.files[0]);
    $('#pdf_url')[0].style.display = 'none';
    document.getElementById('pBar').value = '50';
  } else {
    $('#pdf_url')[0].style.display = 'block';
    $('#img_url')[0].src = "";
    $('#pdf_url')[0].src = (window.URL ? URL : webkitURL).createObjectURL(input.files[0]).concat("#toolbar=0");
    document.getElementById('pBar').value = '50';
  }

  document.getElementById('pBar').value = '75';

  document.getElementById('pBar').value = '100';


}

// post text area auto height while writing
$(".postTextArea").each(function () {
  this.setAttribute("style", "height:120px;overflow-y:hidden;  font-size : 15px;");
}).on("input", function () {
  this.style.height = "120px";
  this.style.height = (this.scrollHeight) + "px";
});





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



// var navbarBtn = document.getElementsByClassName("navbarActiveBtn");

// for (let i1 = 0; i1 < navbarBtn.length; i1++) {

//   $(navbarBtn[i1]).click(function () {
//     $(this).addClass("active");
//   })

// }


