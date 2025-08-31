<script setup lang="ts">
import { onMounted, ref, useSlots, watch } from 'vue';
import VueDragResize from 'vue-drag-resize/src';
import { $t } from '@/locales';
import NodeWrap from './modules/nodes/node-wrap.vue';
import StartNode from './modules/nodes/start-node.vue';

defineOptions({
  name: 'SnailWorkflow'
});

interface Props {
  modelValue?: Workflow.NodeDataType;
  spinning?: boolean;
  disabled?: boolean;
  updated?: boolean;
}

const props = withDefaults(defineProps<Props>(), {
  disabled: false,
  spinning: false,
  updated: false,
  modelValue: () => ({})
});

interface Emits {
  (e: 'update:modelValue', modelValue: Workflow.NodeDataType): void;
  (e: 'save'): void;
  (e: 'cancel'): void;
  (e: 'refresh'): void;
}

const emit = defineEmits<Emits>();

const slots = useSlots();

const zoom = ref<number>(100);
const nodeData = ref<Workflow.NodeDataType>({});

const save = async () => {
  emit('save');
};

const cancel = () => {
  emit('cancel');
};

watch(
  () => props.modelValue,
  val => {
    nodeData.value = val;
  },
  { immediate: true }
);

watch(
  () => nodeData.value,
  val => {
    emit('update:modelValue', val);
  }
);

const onZoom = (n: number) => {
  zoom.value += 10 * n;

  if (n > 0) {
    const workflowBodyDom: HTMLDivElement | null = document.querySelector('.workflow-body');
    if (workflowBodyDom) {
      if (zoom.value <= 160) {
        workflowBodyDom.scrollTo({ left: (280 * zoom.value) / 100, behavior: 'smooth' });
      }

      if (zoom.value > 160) {
        workflowBodyDom.scrollTo({ left: (420 * zoom.value) / 100, behavior: 'smooth' });
      }

      if (zoom.value > 200) {
        workflowBodyDom.scrollTo({ left: (520 * zoom.value) / 100, behavior: 'smooth' });
      }
    }
  }

  if (zoom.value <= 10) {
    zoom.value = 10;
  } else if (zoom.value >= 300) {
    zoom.value = 300;
  }
};

const handleWeel = (e: WheelEvent) => {
  e.preventDefault();
  // @ts-expect-error ts-migrate(2339)
  const wheelDelta = e.wheelDelta;

  if (wheelDelta < 0) {
    onZoom(-1);
  } else {
    onZoom(1);
  }
};

onMounted(() => {
  const workflowDom: HTMLDivElement | null = document.querySelector('.workflow');
  if (workflowDom) {
    workflowDom.onwheel = (ev: WheelEvent) => handleWeel(ev);
  }
});

const onDragging = () => {
  const workflowBodyDom: HTMLDivElement | null = document.querySelector('.workflow-design');
  if (workflowBodyDom) {
    workflowBodyDom.setAttribute('style', `cursor: grabbing; transform: scale(${zoom.value / 100})`);
  }
};

const onDragstop = () => {
  const workflowBodyDom: HTMLDivElement | null = document.querySelector('.workflow-design');
  if (workflowBodyDom) {
    workflowBodyDom.setAttribute('style', `cursor: grab; transform: scale(${zoom.value / 100})`);
  }
};
</script>

<template>
  <div class="workflow">
    <div class="workflow-affix">
      <NAffix :trigger-top="0" class="position-sticky z-2">
        <div class="header">
          <div>
            <NTooltip>
              <template #trigger>
                <NButton size="small" strong circle @click="onZoom(-1)">
                  <icon-ant-design:minus-outlined />
                </NButton>
              </template>
              缩小
            </NTooltip>
            <span class="ml-8px mr-8px text-18px text-#333639 dark:text-#d6d6d6">{{ zoom }}%</span>
            <NTooltip>
              <template #trigger>
                <NButton size="small" strong circle @click="onZoom(1)">
                  <icon-ant-design:plus-outlined />
                </NButton>
              </template>
              放大
            </NTooltip>
          </div>
          <div v-if="!disabled" class="buttons flex-y-center">
            <div v-if="updated" class="flex-y-center gap-6px">
              <NBadge dot processing />
              <span class="mr-16px text-12px text-error">修改未保存</span>
            </div>
            <NButton type="info" @click="save">保存</NButton>
            <NButton class="ml-16px" @click="cancel">取消</NButton>
          </div>
          <div v-if="disabled && slots.buttons" class="buttons">
            <slot name="buttons"></slot>
          </div>
        </div>
      </NAffix>
      <NSpin :show="spinning">
        <div class="workflow-body">
          <VueDragResize
            class="vue-drag"
            :is-draggable="true"
            :is-resizable="false"
            :z="1"
            @dragging="onDragging"
            @dragstop="onDragstop"
          >
            <div class="workflow-design" :style="`transform: scale(${zoom / 100})`">
              <div class="box-scale">
                <StartNode v-model="nodeData" :disabled="disabled" />
                <NodeWrap
                  v-if="nodeData.nodeConfig"
                  v-model="nodeData.nodeConfig"
                  :disabled="disabled"
                  @refresh="() => emit('refresh')"
                />
                <div class="end-node">
                  <div class="end-node-circle"></div>
                  <div class="end-node-text">{{ $t('workflow.node.endNode') }}</div>
                </div>
              </div>
            </div>
          </VueDragResize>
        </div>
      </NSpin>
    </div>
  </div>
</template>

<style lang="scss">
@use './styles/index';

.workflow {
  padding: 0 !important;

  &-affix {
    .header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
      background-color: #fff;
      box-sizing: border-box;
      padding: 8px 16px;
    }
  }

  &-body {
    overflow: hidden;

    .active:before {
      outline: none !important;
    }

    .vue-drag,
    .content-container {
      min-width: 100%;
    }

    .vue-drag .content-container {
      width: unset !important;
      height: unset !important;
    }
  }

  &-design {
    cursor: grab;
    transform-origin: 0 0 !important;
    min-height: calc(100vh - 198px);
    min-width: 100%;
    padding: 16px;
    outline: 1px dashed #d6d6d6;
  }
}

.dark {
  .workflow {
    &-affix {
      .header {
        box-shadow: 0 1px 4px rgba(255, 255, 255, 0.08);
        background-color: #121212;
      }
    }
  }
}

#__SCROLL_EL_ID__ {
  overflow: hidden;
}
</style>
