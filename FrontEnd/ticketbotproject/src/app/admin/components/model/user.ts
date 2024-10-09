export interface User {
    userId: number;
    name: string;
    email: string;
    role?: 'PROJECT_TEAM_MEMBER' | 'IT_SUPPORT_TEAM' | 'REPORTING_MEMBER';
  }