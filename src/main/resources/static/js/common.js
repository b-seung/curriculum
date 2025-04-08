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
  console.log('?');
  $('p[id^="error-"]').each((index, item) => {
    console.log($(item));
    if (!$(item).hasClass('hidden')) {
      $(item).addClass('hidden');
    }
    $(item).text('');
  });

  $('.input-error').each((i, item) => {
    $(item).removeClass('input-error');
  });
};

const postData = (url, form, callback = null) => {
  let params = new Object();
  $(form)
    .find('input')
    .each((index, item) => {
      params[$(item).attr('name')] = $(item).val();
    });

  $.ajax({
    url: '/loging',
    type: 'POST',
    dataType: 'json',
    data: params,
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
