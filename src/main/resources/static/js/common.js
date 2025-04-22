$(window).on('load', () => {
  const windowHeight = $(window).height();
  $('.menu').height(windowHeight - 71);
});

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

const setError = (msg) => {
  if ($('#error-msg').hasClass('hidden')) {
    $('#error-msg').removeClass('hidden');
  }

  $('#error-msg').find('div').html(msg.replaceAll('\n', '<br />'));
};

const setSuccessMsg = (id, msg) => {
  const item = '#' + id;
  const error = '#' + $.escapeSelector('error-' + id);

  if (!$(item).hasClass('input-success')) {
    $($(item).addClass('input-success'));
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

  $('.input-success').each((i, item) => {
    $(item).removeClass('input-success');
  });

  if (!$('#error-msg').hasClass('hidden')) {
    $('#error-msg').addClass('hidden');
  }
  $('#error-msg').find('div').text('');
};

const errorClearTarget = (id) => {
  if (!$('#error-' + id).hasClass('hidden')) {
    $('#error-' + id).addClass('hidden');
  }

  if ($('#' + id).hasClass('input-error')) {
    $('#' + id).removeClass('input-error');
  }
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

const postData = (url, params, success = null, error = null) => {
  $.ajax({
    url: url,
    type: 'POST',
    contentType: 'application/json',
    data: JSON.stringify(params),
    success: function (res) {
      if (success != null) {
        success(res);
      }
    },
    error: function (request) {
      if (error != null) {
        error(request);
      } else {
        setError(request.responseJSON.message);
      }
    },
  });
};

const getData = (url, params, success = null, error = null) => {
  $.ajax({
    url: url,
    type: 'GET',
    contentType: 'application/json',
    data: JSON.stringify(params),
    success: function (res) {
      if (success != null) {
        success(res);
      }
    },
    error: function (request) {
      if (error != null) {
        error(request);
      } else {
        setError(request.responseJSON.message);
      }
    },
  });
};

const onClickMenu = () => {
  const rootStyles = window.getComputedStyle(document.documentElement);
  if ($('#menu').hasClass('close')) {
    $('#menu').removeClass('close');
    $('#menu').addClass('open');

    $('.menu').animate(
      {
        left: '0px',
      },
      1000
    );
  } else {
    $('#menu').removeClass('open');
    $('#menu').addClass('close');

    $('.menu').animate(
      {
        left: rootStyles.getPropertyValue('--menu-width-default'),
      },
      1000
    );
  }
};
