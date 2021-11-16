import { Column, Entity, PrimaryGeneratedColumn, Unique } from 'typeorm';

@Entity({ name: 'Auth' })
export default class Auth {
  @PrimaryGeneratedColumn('increment')
  id: string;

  @Column()
  email: string;

  @Column()
  password: string;
}
