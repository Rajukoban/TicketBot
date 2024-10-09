export interface ITTicket {
    ticketId: number;
    title: string;
    description: string;
    status: string;
    createdDate:string;
    priority: string;
    user: {
      userId: number;
      name: string;
      email: string;
      role: string;
    };
  }
  