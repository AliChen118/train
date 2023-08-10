<template>
  <div className="order-train">
    <span className="order-train-main">{{ dailyTrainTicket.date }}</span>&nbsp;
    <span className="order-train-main">{{ dailyTrainTicket.trainCode }}</span>次&nbsp;
    <span className="order-train-main">{{ dailyTrainTicket.start }}</span>站
    <span className="order-train-main">({{ dailyTrainTicket.startTime }})</span>&nbsp;
    <span className="order-train-main">——</span>&nbsp;
    <span className="order-train-main">{{ dailyTrainTicket.end }}</span>站
    <span className="order-train-main">({{ dailyTrainTicket.endTime }})</span>&nbsp;
  </div>
</template>

<script>

import {defineComponent} from 'vue';

export default defineComponent({
  name: "order-view",
  setup() {
    const dailyTrainTicket = SessionStorage.get(SESSION_ORDER) || {};
    console.log("下单的车次信息", dailyTrainTicket);

    return {
      dailyTrainTicket
    };
  },
});
</script>

<style>
.order-train .order-train-main {
  font-size: 18px;
  font-weight: bold;
}
</style>
