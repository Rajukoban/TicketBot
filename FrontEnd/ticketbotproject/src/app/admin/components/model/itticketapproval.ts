import { ApprovalStatus } from "./approvalStatus";
import { User } from "./user";

export interface ITTicketApproval{
    ticketId: number;
    title: string;
    status: string;
    priority: string;
    approvalStatus: ApprovalStatus;
  }