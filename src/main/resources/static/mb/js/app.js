var errorMsgMap = new Map();

function insert(entity) {
    
    let confirm = window.confirm(CONFIRM_INSERT);
    if (!confirm) {
        return;
    }
    
    clearAlert();

    const formNode = document.getElementById('inputForm');
    const formData = new FormData(formNode);

    fetch(CONTEXT_ROOT + 'rest/' + entity + '/insert', {
        method: 'POST',
        body: formData,
    })
    .then(async (response) => {
        let json = await response.json();
        alertMsg(json)
        if(response.ok) {
            return json;
        } else {
            throw new Error("error");
        }
    })
    .then((json) => {
    })
    .catch((error) => {
    });

}

function update(entity) {
    
    let confirm = window.confirm(CONFIRM_UPDATE);
    if (!confirm) {
        return;
    }
    
    clearAlert();

    const formNode = document.getElementById('inputForm');
    const formData = new FormData(formNode);

    fetch(CONTEXT_ROOT + 'rest/' + entity + '/update', {
        method: 'POST',
        body: formData,
    })
    .then(async (response) => {
        let json = await response.json();
        alertMsg(json)
        if(response.ok) {
            return json;
        } else {
            throw new Error("error");
        }
    })
    .then((json) => {
    })
    .catch((error) => {
    });

}

function remove(entity) {
    
    let confirm = window.confirm(CONFIRM_DELETE);
    if (!confirm) {
        return;
    }
    
    clearAlert();

    const formNode = document.getElementById('inputForm');
    const formData = new FormData(formNode);

    fetch(CONTEXT_ROOT + 'rest/' + entity + '/delete', {
        method: 'POST',
        body: formData,
    })
    .then(async (response) => {
        let json = await response.json();
        alertMsg(json)
        if(response.ok) {
            return json;
        } else {
            throw new Error("error");
        }
    })
    .then((json) => {
        history.back();
    })
    .catch((error) => {
    });

}

function alertMsg(msgObj){
    
    let globalMsgArea = document.getElementById('input-global-error');
    globalMsgArea.textContent = '';
    
    let i = 0;
    let msg = '';
    for (const value of msgObj.globalOrderedMsg) {
        if (i > 0) {
            msg += "\n";
        }
        msg += value;
        let divObj = document.createElement("DIV");
        divObj.textContent = value;
        globalMsgArea.appendChild(divObj);
        i++;
    }
    for (const [key, value] of Object.entries(msgObj.globalMsg)) {
        if (i > 0) {
            msg += "\n";
        }
        msg += value;
        let divObj = document.createElement("DIV");
        divObj.textContent = value;
        globalMsgArea.appendChild(divObj);
        i++;
    }
    
    for (const [key, valueList] of Object.entries(msgObj.fieldOrderedMsg)) {
        for (const value of valueList) {
            if (i > 0) {
                msg += "\n";
            }
            msg += value;
            let divObj = document.createElement("DIV");
            divObj.textContent = value;
            let errorObj = document.getElementById(key + 'Error');
            errorObj.textContent = '';
            errorObj.appendChild(divObj);
            errorMsgMap.set(errorObj, errorObj);
            i++;
        }
    }
    for (const [key, valueList] of Object.entries(msgObj.fieldMsg)) {
        for (const [msgKey, msgValue] of Object.entries(valueList)) {
            if (i > 0) {
                msg += "\n";
            }
            msg += msgValue;
            let divObj = document.createElement("DIV");
            divObj.textContent = msgValue;
            let errorObj = document.getElementById(key + 'Error');
            errorObj.textContent = '';
            errorObj.appendChild(divObj);
            errorMsgMap.set(errorObj, errorObj);
            i++;
        }
    }
    alert(msg);
}

function clearAlert(){
    
    let globalMsgArea = document.getElementById('input-global-error');
    globalMsgArea.textContent = '';
    
    for (const [key, value] of errorMsgMap.entries()) {
        key.textContent = '';
    }
}

function clearErrorMsgMap(){
    errorMsgMap.clear();
}