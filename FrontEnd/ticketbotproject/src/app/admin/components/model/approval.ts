import { ApprovalStatus } from "./approvalStatus";
import { Ticket } from "./ticket";
import { User } from "./user";

export interface Approval{
    approvalId:number;
    ticket:{
        ticketId:number;
        title:string;
        description:string;
        createdDate:string;
        status:string;
        priority:string;
        User:{
            userId:number;
            name:string;
            email:string;
            role:string
        };
    };
    approvedBy:{
        userId:number;
        name:string;
        email:string;
        role:string;
    };
    approvalDate:string;
    approvalStatus:ApprovalStatus;
}