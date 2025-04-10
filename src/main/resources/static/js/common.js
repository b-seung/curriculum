/**
 * エラーメッセージを表示する
 * @param {*} id
 * @param {*} msg
 */
const setErrorMsg = (id, msg) => {
  const item = '#' + id;
  const error = '#' + $.escapeSelector('error-' + id);

  if (!$(item).hasClass('input-error')) {
    $($(item).addClass('input-error'));
  }

  if ($(error).hasClass('hidden')) {
    $(error).removeClass('hidden');
  }

  $(error).text(msg);
};

/**
 * エラーメッセージを初期化する
 */
const errorClear = () => {
  $('p[id^="error-"]').each((index, item) => {
    if (!$(item).hasClass('hidden')) {
      $(item).addClass('hidden');
    }
    $(item).text('');
  });

  $('.input-error').each((i, item) => {
    $(item).removeClass('input-error');
  });
};

const getForm = (form) => {
  let params = new Object();
  $(form)
    .find('input')
    .each((index, item) => {
      if ($(item).attr('name') == undefined) return;
      if (equalsOr($(item).attr('type'), ['radio'])) return;

      params[$(item).attr('name')] = $(item).val();
    });

  $(form)
    .find('input[type=radio]')
    .each((index, item) => {
      if (params[$(item).attr('name')] != undefined) return;

      params[$(item).attr('name')] = $(`input[name=${$(item).attr('name')}]:checked`).val();
    });

  return params;
};

const equalsOr = (txt, arr) => {
  return arr.includes(txt);
};

const postData = (url, form, callback = null) => {
  let params = getForm($(form));

  $.ajax({
    url: url,
    type: 'POST',
    contentType: 'application/json',
    data: JSON.stringify(params),
    success: function (res) {
      if (callback != null) {
        callback(res);
      }
    },
    error: function (request, status, error) {
      console.log(error);
    },
  });
};

const getData = (url, form, callback = null) => {
  let params = getForm($(form));

  $.ajax({
    url: url,
    type: 'GET',
    contentType: 'application/json',
    data: JSON.stringify(params),
    success: function (res) {
      if (callback != null) {
        callback(res);
      }
    },
    error: function (request, status, error) {
      console.log(error);
    },
  });
};
