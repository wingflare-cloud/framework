<script setup lang="ts">
defineOptions({
  name: 'LabelsInput'
});

const path = defineModel<string>('path', {
  required: true
});

const content = defineModel<{ key: string; value: string }[]>('value', {
  required: false,
  default: () => []
});

const onCreate = () => {
  return {
    key: '',
    value: ''
  };
};

const dynamicInputRule = [
  {
    trigger: ['input', 'blur'],
    validator(_: unknown, val: string) {
      if (!val) return new Error('不能为空');
      return true;
    }
  }
];

const dynamicKeyInputRule = [
  {
    trigger: ['input', 'blur'],
    validator(_: unknown, val: string) {
      if (!val) return new Error('不能为空');
      if (val === 'state') return new Error('key 不能为 state');
      const keyCount = content.value.filter(item => item.key === val).length;
      if (keyCount > 1) {
        return new Error('key 不能重复');
      }
      return true;
    }
  }
];
</script>

<template>
  <NDynamicInput v-model:value="content" item-style="margin-bottom: 0;" :on-create="onCreate" #="{ index }">
    <NGrid>
      <NFormItemGi
        :span="11"
        ignore-path-change
        :show-label="false"
        :rule="dynamicKeyInputRule"
        :path="`${path}[${index}].key`"
      >
        <NInput v-model:value="content[index].key" placeholder="key" @keydown.enter.prevent />
      </NFormItemGi>
      <NGi :span="2" class="h-34px text-center lh-34px">:</NGi>
      <NFormItemGi
        :span="11"
        ignore-path-change
        :show-label="false"
        :rule="dynamicInputRule"
        :path="`${path}[${index}].value`"
      >
        <NInput v-model:value="content[index].value" placeholder="value" @keydown.enter.prevent />
      </NFormItemGi>
    </NGrid>
  </NDynamicInput>
</template>

<style scoped></style>
