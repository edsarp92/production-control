var stompClient = null;

function connect(location,subscribe){
    if(stompClient){
        if(stompClient.connected){
                return
        }
    }

    var socket = new SockJS(location);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame){
        console.log('connected:'+frame);
        zAu.send(new zk.Event(zk.Widget.$('$listenButton'),'onConnect',{},{toServer:true}))
        stompClient.subscribe(subscribe,function(greeting){
            console.log(greeting);
            var jsonData = JSON.parse(greeting.body);
            zAu.send(new zk.Event(zk.Widget.$('$listenButton'),'onReceive',jsonData,{toServer:true}))
        });
    });
}

function disconnect(){
    if(stompClient){
        stompClient.disconnect(function(){
            zAu.send(new zk.Event(zk.Widget.$('$listenButton'),'onDisconnect',{},{toServer:true}))
        });
    }
}

function checkStatus(){
    console.log(stompClient);
    if(stompClient){
        if(stompClient.connected){
            zAu.send(new zk.Event(zk.Widget.$('$listenButton'),'onConnect',{},{toServer:true}))
        }else{
            zAu.send(new zk.Event(zk.Widget.$('$listenButton'),'onDisconnect',{},{toServer:true}))
        }
    }
    console.log(stompClient);
}