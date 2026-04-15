# Todo WebSocket 订阅示例

## 1. 安装依赖

```bash
npm i @stomp/stompjs sockjs-client
```

## 2. 消息格式

后端推送消息结构（`TodoNoticeMessage`）：

```json
{
  "action": "CREATED",
  "todoId": 123,
  "bizType": "ORDER_PAY",
  "bizId": "JH202604150001",
  "title": "订单待支付",
  "content": "订单 JH202604150001 已创建，请尽快完成付款。",
  "receiverType": "USER",
  "receiverId": 10001,
  "status": "PENDING",
  "expireTime": "2026-04-15T10:30:00"
}
```

## 3. 用户端订阅示例

```ts
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";

type TodoNoticeMessage = {
  action: "CREATED" | "UPDATED";
  todoId: number;
  bizType: string;
  bizId: string;
  title: string;
  content: string;
  receiverType: "USER" | "ADMIN";
  receiverId?: number;
  status: "PENDING" | "COMPLETED" | "EXPIRED";
  expireTime?: string;
};

const userId = 10001;
let badgeCount = 0;

function refreshBadge(delta: number) {
  badgeCount = Math.max(0, badgeCount + delta);
  const badge = document.getElementById("todo-badge");
  if (badge) badge.textContent = String(badgeCount);
}

function popupNotice(message: TodoNoticeMessage) {
  console.log("[TODO]", message.title, message.content);
  // 这里替换成你们 UI 框架的通知组件
}

const client = new Client({
  webSocketFactory: () => new SockJS("http://localhost:8088/ws/todo"),
  reconnectDelay: 5000,
  onConnect: () => {
    client.subscribe(`/topic/todo/user/${userId}`, (frame) => {
      const message: TodoNoticeMessage = JSON.parse(frame.body);
      if (message.action === "CREATED" && message.status === "PENDING") {
        refreshBadge(1);
      }
      if (
        message.action === "UPDATED" &&
        (message.status === "COMPLETED" || message.status === "EXPIRED")
      ) {
        refreshBadge(-1);
      }
      popupNotice(message);
    });
  },
});

client.activate();
```

## 4. 管理后台订阅示例

后台当前是“公共管理员待办”模式（`receiverId = null`），可订阅公共频道：

```ts
client.subscribe("/topic/todo/admin", (frame) => {
  const message = JSON.parse(frame.body);
  // 与用户端相同逻辑：角标 + 弹窗
});
```

如果后续改为“按管理员 ID 分发”，则改成：

```ts
client.subscribe(`/topic/todo/admin/${adminId}`, (frame) => {
  const message = JSON.parse(frame.body);
});
```

## 5. MQ 模式切换

默认本地事件模式：

```yaml
todo:
  biz-event:
    mode: local
```

切换 RabbitMQ：

```yaml
todo:
  biz-event:
    mode: rabbit
```

`mode=rabbit` 后：

- 订单节点通过 RabbitMQ 发送业务事件
- 待办中心通过队列消费事件
- 仍会触发待办写库 + WebSocket 推送
