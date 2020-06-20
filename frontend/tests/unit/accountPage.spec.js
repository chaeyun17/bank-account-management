import { shallowMount } from '@vue/test-utils'
import AccountPage from '@/views/AccountPage.vue'

describe('AccountPage.vue', () => {
  it('splitByThousand() 테스트', () => {
    expect(AccountPage.methods.splitByThousand(0)).toBe("0");
    expect(AccountPage.methods.splitByThousand(-1)).toBe("0");
    expect(AccountPage.methods.splitByThousand(12)).toBe("12");
    expect(AccountPage.methods.splitByThousand(123)).toBe("123");
    expect(AccountPage.methods.splitByThousand(1234)).toBe("1,234");
    expect(AccountPage.methods.splitByThousand(12345678)).toBe("12,345,678");
  })
})
