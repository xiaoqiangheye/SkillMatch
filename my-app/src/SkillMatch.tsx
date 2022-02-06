import React, {Component} from "react";
import "./SkillMatch.css";

interface SkillMatchState {
    searchValue:string,
    elements: JSX.Element[]
}

class SkillMatch extends Component<{}, SkillMatchState>{
    constructor(props: any) {
        super(props);
        this.state = {
            searchValue:"Python",
            elements: []
        };
        this.onInputChange = this.onInputChange.bind(this);
    }


    async search() {
        try {
            let response = await fetch("http://0.0.0.0:4567/skillmatch?skills=" + this.state.searchValue);
            if (!response.ok) {
                alert("Failure to get Data.");
                return
            }

            let parsed: any = await response.json();
            let elements: JSX.Element[] = [<p/>];
            for (let entry of Object.keys(parsed)) {
                let value = parsed[entry];
                console.log(value)
                let string = value["name"] + "     " + value["p"]["name"] + "      "
                for(let req of value["requires"]) {
                    string = string + req["name"] + "      level:" + req["level"] + "    "
                }
                elements.push(<p>{string}</p>)
            }

            this.setState({
                elements: elements
            })

        }catch (e) {
            alert(e)
        }
    }

    sea = () => {
        this.search()
    }

    onInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        this.setState({
            searchValue: event.target.value
        });
        console.log("change")
    };

    render() {
        return (
            <div id = "skillmatch">
                <h2 id="title">Skill Matcher Prototype</h2>
                <p id="description"></p>
                <input id="searchBar" value={this.state.searchValue}
                       onChange={this.onInputChange}
                />
                <button id="search" onClick={this.sea}>Match His Skills</button>
                <div>
                    {this.state.elements}
                </div>
            </div>
        )
    }

}

export default SkillMatch;