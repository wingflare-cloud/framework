export interface RequestInstanceState {
  /** whether the request is refreshing token */
  refreshTokenFn: Promise<boolean> | null;
  /** the request error message stack */
  errMsgStack: string[];
  /** whether the request is logout */
  isLogout: boolean;
}
