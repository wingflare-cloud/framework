<script setup lang="ts">
const content = defineModel<{ key: string; value: string | number | boolean; type: string }[]>('value', {
  required: true,
  default: () => []
});

const path = defineModel<string>('path', {
  required: true
});

const onCreate = () => {
  return {
    key: '',
    value: '',
    type: 'string'
  };
};

const dynamicInputRule = [
  {
    trigger: ['input', 'blur'],
    validator(_: unknown, value: string | number) {
      if (!value && value !== 0) return new Error('不能为空');
      return true;
    }
  }
];

const typeOptions = [
  {
    label: 'string',
    value: 'string'
  },
  {
    label: 'number',
    value: 'number'
  },
  {
    label: 'boolean',
    value: 'boolean'
  }
];

const boolenOptions = [
  {
    label: 'true',
    value: 1
  },
  {
    label: 'false',
    value: 0
  }
];

const handleUpdateType = (index: number) => {
  if (content.value[index].type === 'string') {
    content.value[index].value = '';
  }

  if (content.value[index].type === 'boolean') {
    content.value[index].value = 0;
  }

  if (content.value[index].type === 'number') {
    content.value[index].value = 0;
  }
};
</script>

<template>
  <NDynamicInput v-model:value="content" item-style="margin-bottom: 0;" :on-create="onCreate" #="{ index }">
    <NGrid>
      <NFormItemGi
        :span="7"
        ignore-path-change
        :show-label="false"
        :rule="dynamicInputRule"
        :path="`${path}[${index}].key`"
      >
        <NInput v-model:value="content[index].key" placeholder="key" @keydown.enter.prevent />
      </NFormItemGi>
      <NGi :span="2" class="h-34px text-center lh-34px">=</NGi>
      <NFormItemGi
        :span="7"
        ignore-path-change
        :show-label="false"
        :rule="dynamicInputRule"
        :path="`${path}[${index}].value`"
      >
        <NInput
          v-if="content[index].type === 'string'"
          v-model:value="content[index].value as string"
          placeholder="value"
          @keydown.enter.prevent
        />
        <NInputNumber
          v-if="content[index].type === 'number'"
          v-model:value="content[index].value as number"
          class="w-full"
          placeholder="value"
          @keydown.enter.prevent
        />
        <NSelect
          v-if="content[index].type === 'boolean'"
          v-model:value="content[index].value as number"
          class="w-full"
          :options="boolenOptions"
          placeholder="value"
          @keydown.enter.prevent
        />
      </NFormItemGi>
      <NFormItemGi
        :span="3"
        class="ml-12px w-115px"
        ignore-path-change
        :show-label="false"
        :path="`${path}[${index}].type`"
      >
        <div class="h-34px lh-34px">(</div>
        <NSelect
          v-model:value="content[index].type"
          class="mx-3px"
          :options="typeOptions"
          placeholder="字段类型"
          @keydown.enter.prevent
          @update:value="handleUpdateType(index)"
        />
        <div class="h-34px lh-34px">)</div>
      </NFormItemGi>
    </NGrid>
  </NDynamicInput>
</template>

<style scoped></style>
