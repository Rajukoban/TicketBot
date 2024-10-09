import { ApprovalStatus } from "./approvalStatus";
import { Ticket } from "./ticket";
import { User } from "./user";

export interface SendApprovalTicket{
        approvalId:number;
        ticket: {
          ticketId: number;
        };
        approvedBy: {
          userId: number;
        };
        approvalStatus: ApprovalStatus;

}