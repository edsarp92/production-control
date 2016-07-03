var stompClientOut = null;

function connectOut(location,subscribe){
    if(stompClientOut){
        if(stompClientOut.connected){
                return
        }
    }

    var socket = new SockJS(location);
    stompClientOut = Stomp.over(socket);
   stompClientOut.connect({}, function(frame){
        console.log('connected:'+frame);
        zAu.send(new zk.Event(zk.Widget.$('$winAuthorization'),'onConnect',{},{toServer:true}))
       stompClientOut.subscribe(subscribe,function(greeting){
            console.log(greeting);
            var jsonData = JSON.parse(greeting.body);
            zAu.send(new zk.Event(zk.Widget.$('$winAuthorization'),'onReceive',jsonData,{toServer:true}))
        });
    });
}

function disconnectOut(){
    if(stompClientOut){
       stompClientOut.disconnect(function(){
            zAu.send(new zk.Event(zk.Widget.$('$winAuthorization'),'onDisconnect',{},{toServer:true}))
        });
    }
}

function checkStatusOut(){
    console.log(stompClientOut);
    if(stompClientOut){
        if(stompClientOut.connected){
            zAu.send(new zk.Event(zk.Widget.$('$winAuthorization'),'onConnect',{},{toServer:true}))
        }else{
            zAu.send(new zk.Event(zk.Widget.$('$winAuthorization'),'onDisconnect',{},{toServer:true}))
        }
    }
    console.log(stompClientOut);
}